# Oraxen Transparent Blocks

> This plugin is a work in progress, many features are not yet finished, including block drops.

This is an addon to the Premium plugin [Oraxen](https://github.com/oraxen/Oraxen). It adds the ability to use custom models that are transparent or don't fill an entire block without render issues.

Native Version: 1.16.1

![With](https://i.imgur.com/JXxGKBj.png)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You must have [Oraxen](https://github.com/oraxen/Oraxen), [WorldEdit](https://github.com/EngineHub/WorldEdit), and [WorldGuard](https://github.com/EngineHub/WorldGuard) installed on your server to use this plugin.

### Installing

Place the plugin jar file in your servers plugins directory.

### Adding Transparent Block Mechanic to Oraxen

Add the following to mechanics.yml in your Oraxen plugin folder

```yaml
transparent_block:
  enabled: true
```

## Configuring Blocks

Create a custom item/block using [Oraxen](https://github.com/oraxen/Oraxen) and give your item/block the transpatent_block mechanic.

### Example:

```yaml
transparent_block:
  displayname: '&7Transparent Block'
  material: DIAMOND
  Pack:
    generate_model: false
    model: transparent_block
  Mechanics:
    transparent_block:
      custom_variation: 25
      armorstand_visible: false
      armorstand_small: false
      block_gravity: false
      break_sound: BLOCK_GLASS_BREAK
      drop:
        loots:
          - {oraxen_item: transparent_block, probability: 1.0, max_amount: 1}
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
    <version>1.1</version>
</dependency>
```

## License

This project is licensed under the GNU GPLv3 License
