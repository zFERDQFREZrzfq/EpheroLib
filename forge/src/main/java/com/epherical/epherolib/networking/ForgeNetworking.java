package com.epherical.epherolib.networking;

import net.minecraft.network.Connection;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.SimpleChannel;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ForgeNetworking extends AbstractNetworking<CustomPayloadEvent.Context, CustomPayloadEvent.Context> {

    private static int id = 0;

    public final SimpleChannel INSTANCE;

    private final ResourceLocation modChannel;
    private final String version;

    public ForgeNetworking(ResourceLocation location, String version, Predicate<String> clientAcceptedVersion,
                           Predicate<String> serverAcceptedVersion) {
        this.modChannel = location;
        this.version = version;

        INSTANCE = ChannelBuilder.named(location).simpleChannel();
    }

    @Override
    public <MSG> void registerServerToClient(int id, Class<MSG> type, BiConsumer<MSG, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, MSG> decoder, BiConsumer<MSG, Context<CustomPayloadEvent.Context>> consumer) {
        INSTANCE.messageBuilder(type, id)
                .encoder(encoder)
                .decoder(decoder)
                .consumerNetworkThread((msg, context) -> {
                    context.getSender();
                    Side side = context.getDirection().getReceptionSide().isServer() ? Side.SERVER : Side.CLIENT;
                    consumer.accept(msg, new Context<>(side, context.getSender()));
                }).add();
    }

    @Override
    public <T> void registerClientToServer(int id, Class<T> type, BiConsumer<T, FriendlyByteBuf> encoder, Function<FriendlyByteBuf, T> decoder, BiConsumer<T, Context<CustomPayloadEvent.Context>> consumer) {
        INSTANCE.messageBuilder(type, id)
                .encoder(encoder)
                .decoder(decoder)
                .consumerNetworkThread((t, context) -> {
                    Side side = context.getDirection().getReceptionSide().isServer() ? Side.SERVER : Side.CLIENT;
                    consumer.accept(t, new Context<>(side, context.getSender()));
                });
    }

    @Override
    public <T> void sendToClient(T type, ServerPlayer serverPlayer) {
        INSTANCE.send(type, PacketDistributor.PLAYER.with(serverPlayer));
    }

    @Override
    public <T> void sendToServer(T type, Connection connection) {
        INSTANCE.send(type, connection);
    }
}
