# EpheroLib

To Depend on this library: 

For all projects you will need to access the repo

```
maven {
    name "Epherical Maven"
    url "https://maven.epherical.com/releases"
}
```

In the root project:

```
dependencies {
    compileOnlyApi('com.epherical.epherolib:EpheroLib:1.0.0-1.20.1')
}
```
In Fabric:
```
dependencies {
    modImplementation('com.epherical.epherolib:EpheroLib-fabric:1.0.0-1.20.1')
}
```
In Forge:
```
dependencies {
    implementation (fg.deobf('com.epherical.epherolib:EpheroLib-forge:1.0.0-1.20.1'))
}
```
