# PintoTools

A Minecraft server plugin providing useful commands for player management, item editing, and utility functions.

## Features

- **Heal**: Restore health to yourself or another player.
- **Feed**: Replenish hunger for yourself or another player.
- **Fly**: Toggle flight mode for yourself or others.
- **ItemEdit**: Edit the item in your hand (name, lore, attributes, clear).
- **SummonNoAI**: Summon mobs without AI.
- **Timer**: Start a timer.
- **GracePeriod**: Start a grace period timer, disables PvP for the duration.
- **InvSee**: View another player's inventory.

## Installation

1. Download the latest `PintoTools-1.0.jar` from the `build/libs/` directory.
2. Place the jar file into your server's `plugins` folder.
3. Restart your server.

## Usage

Each command can be used in-game. Example usages:

- `/heal [player]`
- `/feed [player]`
- `/fly [player]`
- `/itemedit <name|lore|attribute|clear>`
- `/summonnoai <mob>`
- `/timer <seconds>`
- `/graceperiod <seconds>`
- `/invsee <player>`

Aliases are available for most commands (see `plugin.yml`).

## Permissions

| Command        | Permission                |
|----------------|--------------------------|
| heal           | pintotools.heal          |
| feed           | pintotools.feed          |
| fly            | pintotools.fly           |
| itemedit       | pintotools.itemedit      |
| summonnoai     | pintotools.summonnoai    |
| timer          | pintotools.timer         |
| graceperiod    | pintotools.graceperiod   |
| invsee         | pintotools.invsee        |

## Authors

- PintoJ

## Compatibility

- Minecraft API version: 1.21

---