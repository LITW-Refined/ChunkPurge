ChunkPurge
==========

A Minecraft Forge mod to periodically unload chunks from the server. Attempts to recreate the functionality of Cauldron's ChunkGC.

Download
========

Not required on the client, although may work in single player.
Download the latest release here: https://github.com/Pilzinsel64/ChunkPurge/releases/latest

**PLEASE NOTE:** I personally don't use this mod anymore on my own server since I [prevent chunk loaded on request](https://github.com/Pilzinsel64/PilzMcSammelsorium#disable-chunk-loading-on-request) using my own [PilzMcSammelsoium mod](https://github.com/Pilzinsel64/PilzMcSammelsorium) wich helps to prevent the main reason why I used this mod: Ghost loaded chunks.

Description
===========

This mod works by periodically scanning the loaded chunks in each dimension that are configured to be scanned, and identifying those which are isolated from any valid chunk watchers. Those chunks are then scheduled for unloading. Valid chunk watchers are players, chunkloading tickets (force loaded chunks), and the spawn areas of certain dimensions. 

By only unloading isolated chunks, we avoid breaking energy nets and other multi-chunk objects. Partially unloading these would cause significant lag spikes. A flood fill algorithm is used to identify which chunks are still linked to chunk watchers, and the remaining chunks are unloaded.

Commands
========

These configuration options can also be edited in ChunkPurge.cfg. For all commands omit the parameter to view the current setting.

```
/chunkpurge delay [ticks]
```
The number of ticks to wait between chunk unloading attempts.
Default: 600

```
/chunkpurge debug [true|false]
```
Logs to console the number of chunks unloaded from each dimension, and how long is spent calculating which chunks to unload.
Default: false

```
/chunkpurge enable [true|false]
```
Setting to false will prevent any attempts to unload chunks.
Default: true

```
/chunkpurge pradius [# of chunks]
```
Sets the ignore radius around each player. Ignore radius for the player player is the sum of server set player view distance and pradius.
Chunks outside the ignore radius will be forced to unload regardless of whether or not those chunks are connected to player loaded chunks.
Default: 4

```
/chunkpurge tradius [# of chunks]
```
Sets the ignore radius around each force loaded chunk (chunk loading ticket).
Chunks outside the ignore radius will be forced to unload regardless of whether or not those chunks are connected to force loaded chunks.
Default: 5

```
/chunkpurge sradius [# of chunks]
```
Sets the ignore radius around spawn loaded chunks. Ignore radius for spawn is the sum of spawn loaded chunks (8) and sradius
Chunks outside the ignore radius will be forced to unload regardless of whether or not those chunks are connected to spawn loaded chunks.
Default: 3

```
/chunkpurge dimlist
```
Lists the current dimension ID's being checked by ChunkPurge for unload.
The default is set to only check the Overworld (dim 0) for unload.
Default: 0

```
/chunkpurge dimlist add [DimID,DimID,ect.]
```
Adds dimension ID's to be checked by ChunkPurge for unload. The default is set to only unload chunks in the overworld (dim 0). However, using /chunkpurge dimlist add -1,1 would set the Nether and End to be checked as well.
Any number of dimID's can be added at one time in a comma seperated list with NO spaces.

```
/chunkpurge dimlist remove [DimID,DimID,ect.]
```
Removes dimension ID's to be checked by ChunkPurge for unload. If the the config is set to check Overworld, Nether, and End, using /chunkpurge dimlist remove -1,1 would set the Nether and End to no longer be checked.
Any number of dimID's can be removed at one time in a comma seperated list with NO spaces.









