package io.github.akashiikun.keepxp;

import io.github.akashiikun.keepxp.mixin.BooleanRuleInvoker;
import io.github.akashiikun.keepxp.mixin.GameRulesInvoker;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.world.GameRules;

public class KeepXPOnDeathMod implements ModInitializer {
    public static GameRules.Key<GameRules.BooleanRule> dropXPOnDeath = GameRulesInvoker.invokeRegister("dropXPOnDeath", GameRules.Category.PLAYER, BooleanRuleInvoker.invokeCreate(true));

    @Override
    public void onInitialize() {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            System.out.println("KeepXPOnDeath is loaded!");
        }
    }
}
