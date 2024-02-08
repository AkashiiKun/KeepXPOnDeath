package io.github.akashiikun.keepxp.mixin;

import io.github.akashiikun.keepxp.KeepXPOnDeathMod;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public class KeepXpOnRespawnMixin {

    @Inject(method = "copyFrom", at = @At("RETURN"))
    private void noXpToDrop(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info) {
        if (!oldPlayer.getWorld().getGameRules().getBoolean(KeepXPOnDeathMod.dropXPOnDeath)) {
            ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

            player.experienceLevel = oldPlayer.experienceLevel;
            player.totalExperience = oldPlayer.totalExperience;
            player.experienceProgress = oldPlayer.experienceProgress;
            player.setScore(oldPlayer.getScore());
        }
    }
}
