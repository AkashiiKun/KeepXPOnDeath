package io.github.akashiikun.keepxp.mixin;

import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(GameRules.class)
public interface GameRulesInvoker {

    @Invoker
    static <T extends GameRules.Rule<T>> GameRules.Key<T> invokeRegister(String name, GameRules.Category category, GameRules.Type<T> type) {
        throw new AssertionError();
    }
}
