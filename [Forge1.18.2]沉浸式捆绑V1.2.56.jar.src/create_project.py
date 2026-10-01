import os

base = r"C:\Users\小亮傻逼\Desktop\ImmersiveRestraints-1.20.1"

build_gradle = r"""plugins {
    id 'eclipse'
    id 'idea'
    id 'maven-publish'
    id 'net.minecraftforge.gradle' version '[6.0,6.2)'
}

version = '1.2.56'
group = 'com.dev.am'
archivesBaseName = 'ImmersiveRestraints'

java.toolchain.languageVersion = JavaLanguageVersion.of(17)

minecraft {
    mappings channel: 'official', version: '1.20.1'
    copyIdeResources = true
    runs {
        client {
            workingDirectory project.file('run')
            property 'forge.logging.markers', 'REGISTRIES'
            property 'forge.logging.console.level', 'debug'
            property 'forge.enabledGameTestNamespaces', 'immersive_restraints'
            mods {
                immersive_restraints { sourceSet sourceSets.main }
            }
        }
        server {
            workingDirectory project.file('run')
            property 'forge.logging.markers', 'REGISTRIES'
            property 'forge.logging.console.level', 'debug'
            property 'forge.enabledGameTestNamespaces', 'immersive_restraints'
            mods {
                immersive_restraints { sourceSet sourceSets.main }
            }
        }
        data {
            workingDirectory project.file('run')
            property 'forge.logging.markers', 'REGISTRIES'
            property 'forge.logging.console.level', 'debug'
            args '--mod', 'immersive_restraints', '--all', '--output', file('src/generated/resources/'), '--existing', file('src/main/resources/')
            mods {
                immersive_restraints { sourceSet sourceSets.main }
            }
        }
    }
}

sourceSets.main.resources { srcDir 'src/generated/resources' }

repositories {
    maven { url = 'https://maven.theillusivec4.top/' }
    maven { url = 'https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/' }
    maven { url = 'https://maven.kosmx.dev/' }
}

dependencies {
    minecraft 'net.minecraftforge:forge:1.20.1-47.3.0'
    implementation fg.deobf('top.theillusivec4.curios:curios-forge:5.9.0+1.20.1')
    implementation fg.deobf('software.bernie.geckolib:geckolib-forge-1.20.1:4.4.4')
    implementation fg.deobf('dev.kosmx.player-anim:player-animation-lib-forge:1.0.2+1.20.1')
}

jar {
    manifest {
        attributes([
            'Specification-Title'     : 'immersive_restraints',
            'Specification-Vendor'    : 'ImmersiveRestraints',
            'Specification-Version'   : '1',
            'Implementation-Title'    : project.name,
            'Implementation-Version'  : project.jar.archiveVersion,
            'Implementation-Vendor'   : 'ImmersiveRestraints',
            'Implementation-Timestamp': new Date().format('yyyy-MM-ddTHH:mm:ssZ')
        ])
    }
}

jar.finalizedBy('reobfJar')
"""

mods_toml = r"""modLoader="javafml"
loaderVersion="[47,)"
license="All Rights Reserved"

[[mods]]
modId="immersive_restraints"
version="1.2.56"
displayName="Immersive Restraints"
description='''
A Minecraft mod that adds immersive bondage and restraint mechanics.
'''

[[dependencies.immersive_restraints]]
    modId="forge"
    mandatory=true
    versionRange="[47,)"
    ordering="NONE"
    side="BOTH"

[[dependencies.immersive_restraints]]
    modId="minecraft"
    mandatory=true
    versionRange="[1.20.1,1.21)"
    ordering="NONE"
    side="BOTH"

[[dependencies.immersive_restraints]]
    modId="curios"
    mandatory=true
    versionRange="[5.9.0,)"
    ordering="NONE"
    side="BOTH"

[[dependencies.immersive_restraints]]
    modId="geckolib"
    mandatory=true
    versionRange="[4.4.0,)"
    ordering="NONE"
    side="BOTH"

[[dependencies.immersive_restraints]]
    modId="playeranimator"
    mandatory=true
    versionRange="[1.0.2,)"
    ordering="NONE"
    side="BOTH"
"""

gradle_properties = """org.gradle.jvmargs=-Xmx3G
org.gradle.daemon=false
"""

settings_gradle = """pluginManagement {
    repositories {
        gradlePluginPortal()
        maven { url = 'https://maven.minecraftforge.net/' }
    }
}
"""

os.makedirs(base, exist_ok=True)

with open(os.path.join(base, 'build.gradle'), 'w', encoding='utf-8') as f:
    f.write(build_gradle)

with open(os.path.join(base, 'gradle.properties'), 'w', encoding='utf-8') as f:
    f.write(gradle_properties)

with open(os.path.join(base, 'settings.gradle'), 'w', encoding='utf-8') as f:
    f.write(settings_gradle)

meta_inf = os.path.join(base, 'src', 'main', 'resources', 'META-INF')
os.makedirs(meta_inf, exist_ok=True)
with open(os.path.join(meta_inf, 'mods.toml'), 'w', encoding='utf-8') as f:
    f.write(mods_toml)

print("Project files created successfully!")
print(f"  build.gradle: {os.path.getsize(os.path.join(base, 'build.gradle'))} bytes")
print(f"  mods.toml: {os.path.getsize(os.path.join(meta_inf, 'mods.toml'))} bytes")
print(f"  gradle.properties: {os.path.getsize(os.path.join(base, 'gradle.properties'))} bytes")