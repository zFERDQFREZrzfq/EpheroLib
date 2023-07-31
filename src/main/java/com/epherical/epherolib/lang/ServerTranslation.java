package com.epherical.epherolib.lang;

import com.epherical.epherolib.config.LanguageConfig;
import com.google.common.collect.ImmutableList;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.network.chat.contents.TranslatableFormatException;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerTranslation extends TranslatableContents {

    private final Language language;

    private List<FormattedText> decomposedParts = ImmutableList.of();

    private static final FormattedText TEXT_PERCENT = FormattedText.of("%");
    private static final FormattedText TEXT_NULL = FormattedText.of("null");
    private static final Pattern FORMAT_PATTERN = Pattern.compile("%(?:(\\d+)\\$)?([A-Za-z%]|$)");

    public ServerTranslation(Language language, String key, @Nullable String fallback, Object[] args) {
        super(key, fallback, args);
        this.language = language;
    }


    protected void decomposePieces() {
        String msg = this.fallback != null ? language.getOrDefault(this.getKey(), this.fallback) : language.getOrDefault(this.getKey());

        try {
            ImmutableList.Builder<FormattedText> builder = ImmutableList.builder();
            Objects.requireNonNull(builder);
            this.decomposeTemplate(msg, builder::add);
            this.decomposedParts = builder.build();
        } catch (TranslatableFormatException e) {
            this.decomposedParts = ImmutableList.of(FormattedText.of(msg));
        }
    }

    private void decomposeTemplate(String string, Consumer<FormattedText> consumer) {
        Matcher matcher = FORMAT_PATTERN.matcher(string);

        try {
            int i = 0;

            int j;
            int l;
            for (j = 0; matcher.find(j); j = l) {
                int k = matcher.start();
                l = matcher.end();
                String string2;
                if (k > j) {
                    string2 = string.substring(j, k);
                    if (string2.indexOf(37) != -1) {
                        throw new IllegalArgumentException();
                    }

                    consumer.accept(FormattedText.of(string2));
                }

                string2 = matcher.group(2);
                String string3 = string.substring(k, l);
                if ("%".equals(string2) && "%%".equals(string3)) {
                    consumer.accept(TEXT_PERCENT);
                } else {
                    if (!"s".equals(string2)) {
                        throw new TranslatableFormatException(this, "Unsupported format: '" + string3 + "'");
                    }

                    String string4 = matcher.group(1);
                    int m = string4 != null ? Integer.parseInt(string4) - 1 : i++;
                    consumer.accept(this.argument(m));
                }
            }

            if (j < string.length()) {
                String string5 = string.substring(j);
                if (string5.indexOf(37) != -1) {
                    throw new IllegalArgumentException();
                }

                consumer.accept(FormattedText.of(string5));
            }

        } catch (IllegalArgumentException var12) {
            throw new TranslatableFormatException(this, var12);
        }
    }

    public FormattedText argument(int i) {
        if (i >= 0 && i < this.getArgs().length) {
            Object object = this.getArgs()[i];
            if (object instanceof Component) {
                return (Component) object;
            } else {
                return object == null ? TEXT_NULL : FormattedText.of(object.toString());
            }
        } else {
            throw new TranslatableFormatException(this, i);
        }
    }

    public <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> styledContentConsumer, Style style) {
        this.decomposePieces();
        Optional<T> optional = Optional.empty();
        for (FormattedText decomposedPart : this.decomposedParts) {
            optional = decomposedPart.visit(styledContentConsumer, style);
        }
        return optional;

    }

    public <T> Optional<T> visit(FormattedText.ContentConsumer<T> contentConsumer) {
        this.decomposePieces();
        Optional<T> optional = Optional.empty();
        for (FormattedText decomposedPart : this.decomposedParts) {
            optional = decomposedPart.visit(contentConsumer);
        }
        return optional;
    }

    public MutableComponent resolve(@Nullable CommandSourceStack commandSourceStack, @Nullable Entity entity, int i) throws CommandSyntaxException {
        Object[] objects = new Object[this.getArgs().length];

        for (int j = 0; j < objects.length; ++j) {
            Object object = this.getArgs()[j];
            if (object instanceof Component) {
                objects[j] = ComponentUtils.updateForEntity(commandSourceStack, (Component) object, entity, i);
            } else {
                objects[j] = object;
            }
        }

        return MutableComponent.create(new ServerTranslation(language, this.getKey(), this.fallback, objects));
    }

    public boolean equals(Object object) {
        if (this == object) {
            return true;
        } else {
            if (object instanceof ServerTranslation contents) {
                return Objects.equals(this.getKey(), contents.getKey()) && Objects.equals(this.fallback, contents.fallback) && Arrays.equals(this.getArgs(), contents.getArgs());
            }
            return false;
        }
    }

    public String toString() {
        return "Stranslation{key='" + this.getKey() + "'" + (this.fallback != null ? ", fallback='" + this.fallback + "'" : "") + ", args=" + Arrays.toString(this.getArgs()) + "}";
    }

    public static MutableComponent translatable(ServerPlayer player, String key) {
        return MutableComponent.create(new ServerTranslation(LanguageConfig.getLanguage(player), key, null, TranslatableContents.NO_ARGS));
    }

    public static MutableComponent translatable(ServerPlayer player, String key, Object... args) {
        return MutableComponent.create(new ServerTranslation(LanguageConfig.getLanguage(player), key, null, args));
    }

    public static MutableComponent translatableWithFallback(ServerPlayer player, String key, @Nullable String fallback) {
        return MutableComponent.create(new ServerTranslation(LanguageConfig.getLanguage(player), key, fallback, TranslatableContents.NO_ARGS));
    }

    public static MutableComponent translatableWithFallback(ServerPlayer player, String key, @Nullable String fallback, Object... args) {
        return MutableComponent.create(new ServerTranslation(LanguageConfig.getLanguage(player), key, fallback, args));
    }
}
