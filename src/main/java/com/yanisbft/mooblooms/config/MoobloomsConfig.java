package com.yanisbft.mooblooms.config;

import me.sargunvohra.mcmods.autoconfig1u.ConfigData;
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;

@Config(name = "mooblooms")
public class MoobloomsConfig implements ConfigData {
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom dandelionMoobloom = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom poppyMoobloom = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom blueOrchidMoobloom = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom alliumMoobloom = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom oxeyeDaisyMoobloom = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom cornflowerMoobloom = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public DamagingMoobloom witherRoseMoobloom = new DamagingMoobloom(80, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom suncower = new BaseMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom bambmoo = new BaseMoobloom(60, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public DamagingMoobloom cowctus = new DamagingMoobloom(10, 2, 4);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom crimsonMooshroom = new BaseMoobloom(100, 4, 8);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom warpedMooshroom = new BaseMoobloom(100, 4, 8);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom redCluckshroom = new BaseMoobloom(10, 4, 8);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom brownCluckshroom = new BaseMoobloom(10, 4, 8);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom crimsonCluckshroom = new BaseMoobloom(100, 4, 8);
	@ConfigEntry.Gui.CollapsibleObject public BaseMoobloom warpedCluckshroom = new BaseMoobloom(100, 4, 8);

	public static class BaseMoobloom implements MoobloomConfigCategory {
		public int weight;
		public int minGroupSize;
		public int maxGroupSize;
		public boolean spawnBlocks;

		public BaseMoobloom(int weight, int minGroupSize, int maxGroupSize) {
			this.weight = weight;
			this.minGroupSize = minGroupSize;
			this.maxGroupSize = maxGroupSize;
			this.spawnBlocks = true;
		}
	}

	public static class DamagingMoobloom implements MoobloomConfigCategory {
		public int weight;
		public int minGroupSize;
		public int maxGroupSize;
		public boolean spawnBlocks;
		public boolean damagePlayers;

		public DamagingMoobloom(int weight, int minGroupSize, int maxGroupSize) {
			this.weight = weight;
			this.minGroupSize = minGroupSize;
			this.maxGroupSize = maxGroupSize;
			this.spawnBlocks = true;
			this.damagePlayers = true;
		}
	}
}
