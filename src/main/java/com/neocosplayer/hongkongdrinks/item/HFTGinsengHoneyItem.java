
package com.neocosplayer.hongkongdrinks.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.item.UseAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.Food;
import net.minecraft.entity.LivingEntity;

import java.util.Map;
import java.util.HashMap;

import com.neocosplayer.hongkongdrinks.procedures.VitaLemonTeaFoodEatenProcedure;
import com.neocosplayer.hongkongdrinks.itemgroup.HongKongDrinksItemGroup;
import com.neocosplayer.hongkongdrinks.HongkongdrinksModElements;

@HongkongdrinksModElements.ModElement.Tag
public class HFTGinsengHoneyItem extends HongkongdrinksModElements.ModElement {
	@ObjectHolder("hongkongdrinks:hft_ginseng_honey")
	public static final Item block = null;
	public HFTGinsengHoneyItem(HongkongdrinksModElements instance) {
		super(instance, 42);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new FoodItemCustom());
	}
	public static class FoodItemCustom extends Item {
		public FoodItemCustom() {
			super(new Item.Properties().group(HongKongDrinksItemGroup.tab).maxStackSize(64)
					.food((new Food.Builder()).hunger(4).saturation(0.3f).build()));
			setRegistryName("hft_ginseng_honey");
		}

		@Override
		public UseAction getUseAction(ItemStack par1ItemStack) {
			return UseAction.DRINK;
		}

		@Override
		public net.minecraft.util.SoundEvent getEatSound() {
			return net.minecraft.util.SoundEvents.ENTITY_GENERIC_DRINK;
		}

		@Override
		public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
			ItemStack retval = super.onItemUseFinish(itemStack, world, entity);
			double x = entity.getPosX();
			double y = entity.getPosY();
			double z = entity.getPosZ();
			{
				Map<String, Object> $_dependencies = new HashMap<>();
				$_dependencies.put("entity", entity);
				VitaLemonTeaFoodEatenProcedure.executeProcedure($_dependencies);
			}
			return retval;
		}
	}
}
