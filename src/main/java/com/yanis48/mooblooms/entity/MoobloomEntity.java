package com.yanis48.mooblooms.entity;

import com.yanis48.mooblooms.Mooblooms;

import net.minecraft.block.BambooBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.enums.BambooLeaves;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class MoobloomEntity extends CowEntity {
	public final String id;
	
	public MoobloomEntity(EntityType<? extends MoobloomEntity> entityType, World world) {
		super(entityType, world);
		this.id = Registry.ENTITY_TYPE.getId(entityType).getPath();
	}
	
	public boolean interactMob(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getStackInHand(hand);
		if (stack.getItem() == Items.SHEARS && this.getBreedingAge() >= 0) {
			this.world.addParticle(ParticleTypes.EXPLOSION, this.x, this.y + this.getHeight() / 2.0F, this.z, 0.0D, 0.0D, 0.0D);
			if (!this.world.isClient) {
				this.remove();
				CowEntity cow = EntityType.COW.create(this.world);
				cow.setPositionAndAngles(this.x, this.y, this.z, this.yaw, this.pitch);
				cow.setHealth(this.getHealth());
				cow.field_6283 = this.field_6283;
				if (this.hasCustomName()) {
					cow.setCustomName(this.getCustomName());
				}
				this.world.spawnEntity(cow);
				for(int i = 0; i < 5; ++i) {
					this.world.spawnEntity(new ItemEntity(this.world, this.x, this.y + this.getHeight(), this.z, new ItemStack(this.getFlowerType().getBlock())));
				}
				stack.damage(1, player, ((playerEntity) -> {
					playerEntity.sendToolBreakStatus(hand);
				}));
				this.playSound(SoundEvents.ENTITY_MOOSHROOM_SHEAR, 1.0F, 1.0F);
			}
			return true;
		} else {
			return super.interactMob(player, hand);
		}
	}
	
	@Override
	public MoobloomEntity createChild(PassiveEntity entity) {
		return (MoobloomEntity) Registry.ENTITY_TYPE.get(new Identifier(Mooblooms.MOD_ID, this.id)).create(this.world);
	}
	
	@Override
	public boolean isPotionEffective(StatusEffectInstance statusEffectInstance) {
		if (statusEffectInstance.getEffectType() == StatusEffects.WITHER && this.id.equals("wither_rose_moobloom")) {
			return false;
		}
		return super.isPotionEffective(statusEffectInstance);
	}
	
	public void tickMovement() {
		if (this.world.isClient && this.id.equals("wither_rose_moobloom")) {
			for(int i = 0; i < 3; ++i) {
				this.world.addParticle(ParticleTypes.SMOKE, this.x + (this.random.nextDouble() - 0.5D) * (double)this.getWidth(), this.y + this.random.nextDouble() * (double)this.getHeight(), this.z + (this.random.nextDouble() - 0.5D) * (double)this.getWidth(), 0.0D, 0.0D, 0.0D);
			}
		}
		super.tickMovement();
	}
	
	public BlockState getFlowerType() {
		BlockState state;
		if (this.id.equals("bambmoo")) {
			state = Blocks.BAMBOO.getDefaultState().with(BambooBlock.LEAVES, BambooLeaves.SMALL);
		} else {
			String flowerId = this.id.replace("_moobloom", "");
			state = Registry.BLOCK.get(new Identifier("minecraft", flowerId)).getDefaultState();
		}
		return state;
	}
}
