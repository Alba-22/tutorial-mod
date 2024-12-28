package dev.alba.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BottleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties MOD_FOOD = new FoodProperties
            .Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.20f)
            // Allow to eat even with food bar full
            .alwaysEdible()
            // Useful for like a stew or drink from a bottle, where you receive an item back
            // .usingConvertsTo(new BottleItem(new Item.Properties()))
            .usingConvertsTo(Items.GLASS_BOTTLE)
            .build();
}
