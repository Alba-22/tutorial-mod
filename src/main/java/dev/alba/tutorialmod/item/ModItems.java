package dev.alba.tutorialmod.item;

import dev.alba.tutorialmod.TutorialMod;
import dev.alba.tutorialmod.item.custom.ChiselItem;
import dev.alba.tutorialmod.item.custom.FuelItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModItems {
    // Indicates to Minecraft which items this mod provides to the game
    // DeferredRegister can be used to Items, Blocks, Creative Menu tabs, etc.
    // Since its "deferred", it will be only created in the game when its needed
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS,
            TutorialMod.MOD_ID
    );

    // Create an item
    public static final RegistryObject<Item> ALEXANDRITE = ITEMS.register(
            "alexandrite",
            () -> new Item(new Item.Properties())
    );
    public static final RegistryObject<Item> RAW_ALEXANDRITE = ITEMS.register(
        "raw_alexandrite",
            () -> new Item(new Item.Properties())
    );

    public static final RegistryObject<Item> CHISEL = ITEMS.register(
            "chisel",
            // The durability property is a data component, which means that the item can only stacks to one
            () -> new ChiselItem(new Item.Properties().durability(32))
    );

    // A common food. If it's desired to have a drink, create a custom item class that extends Item and overrides the
    // getUseAnimation method, returning a UseAnim.DRINK.
    public static final RegistryObject<Item> MOD_FOOD = ITEMS.register(
        "mod_food",
            () -> new Item(new Item.Properties().food(ModFoodProperties.MOD_FOOD)) {
                // Anonymous Class - Allows to override any methods of the parent class(Item, in this case)

                @Override
                public void appendHoverText(ItemStack pStack, TooltipContext pContext, List<Component> pTooltipComponents, TooltipFlag pTooltipFlag) {
                    pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.mod_food"));
                    super.appendHoverText(pStack, pContext, pTooltipComponents, pTooltipFlag);
                }
            }
    );

    public static final RegistryObject<Item> AURORA_ASHES = ITEMS.register(
        "aurora_ashes",
            // To see all minecraft item's burn time, search for the AbstractFurnaceBlockEntity class
            // For comparison, coal burn time is 1600 and it can burn completely 8 items. So 1600/8 = 200 of burn time per item
            () -> new FuelItem(new Item.Properties(), 1200)
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
