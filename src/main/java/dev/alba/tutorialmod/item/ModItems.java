package dev.alba.tutorialmod.item;

import dev.alba.tutorialmod.TutorialMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
