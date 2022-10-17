package dev.chaws.rotationtool.listeners;

import dev.chaws.rotationtool.RotationToolPlugin;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.util.Objects;

public class PlayerListener implements Listener {
	@EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			return;
		}

		if (!Objects.equals(event.getHand(), EquipmentSlot.HAND)) {
			return;
		}

		var block = event.getClickedBlock();
		if (block == null) {
			return;
		}

		if (!block.getType().name().contains("GLAZED")) {
			return;
		}

		var player = event.getPlayer();
		var item = player.getInventory().getItemInMainHand();
		if (item.getType() != Material.COMPASS) {
			return;
		}

		if (!(block.getBlockData() instanceof Directional directional)) {
			return;
		}

		RotationToolPlugin.instance.getServer().getScheduler().runTaskLater(RotationToolPlugin.instance, () -> {
			var facing = directional.getFacing();
			directional.setFacing(switch (facing) {
				case NORTH -> BlockFace.EAST;
				case EAST -> BlockFace.SOUTH;
				case SOUTH -> BlockFace.WEST;
				case WEST -> BlockFace.NORTH;
				default -> facing;
			});

			block.setBlockData(directional);
		}, 0L);

		event.setCancelled(true);
	}
}
