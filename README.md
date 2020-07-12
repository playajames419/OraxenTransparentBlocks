# Oraxen Transparent Blocks

> This plugin is a work in progress, many features are not yet finished, including block drops.

This is an addon to the Premium plugin [Oraxen](https://github.com/oraxen/Oraxen). It adds the ability to use custom models that are transparent or don't fill an entire block without render issues.

Native Version: 1.16.1

![With](https://i.imgur.com/fiwYM3M.png)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You must have [Oraxen](https://github.com/oraxen/Oraxen), [WorldEdit](https://github.com/EngineHub/WorldEdit), and [WorldGuard](https://github.com/EngineHub/WorldGuard) installed on your server to use this plugin.

### Installing

Place the plugin jar file in your servers plugins directory.

## Configuring Blocks

Create a custom item/block using [Oraxen](https://github.com/oraxen/Oraxen) and give your item/block the transpatent_block mechanic.

### Example:

```yaml
test-block:
  displayname: '&7Test Block'
  material: STICK
  Pack:
    generate_model: true
    parent_model: block/cube_all
    textures:
    - sweet_test_texture
    custom_model_data: 1
  Mechanics:
    transparent_block:
      # These settings arent working yet
      armorstand_visible: true
      armorstand_small: true
caveblock:
  displayname: '&7Cave Block'
  material: DIAMOND
  Pack:
    generate_model: false
    model: caveblock
    custom_model_data: 1
  Mechanics:
    transparent_block:
      # These settings arent working yet
      armorstand_visible: false
      armorstand_small: true
      drops:
```

## Block Models

When creating custom models to use with this plugin, you will need to modify the armorstand's helmet display settings to your likings. This is how the block will show in-game.

## Developers

Although not very extensive, this plugin call's the following custom events for developers to use;

* OraxenTransparentBlockInteractEvent
* OraxenTransparentBlockPrePlaceEvent
* OraxenTransparentBlockPlaceEvent
* OraxenTransparentBlockPreBreakEvent
* OraxenTransparentBlockBreakEvent

### Add OraxenTransparentBlocks to your plugin

```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
  
<dependency>
    <groupId>com.github.playajames419</groupId>
    <artifactId>OraxenTransparentBlocks</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

## License

This project is licensed under the GPL License
