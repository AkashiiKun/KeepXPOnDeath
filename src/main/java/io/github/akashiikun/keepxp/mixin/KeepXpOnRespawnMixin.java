package io.github.akashiikun.keepxp.mixin;

import com.mojang.authlib.GameProfile;
import io.github.akashiikun.keepxp.KeepXPOnDeathMod;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(ServerPlayerEntity.class)
public abstract class KeepXpOnRespawnMixin extends PlayerEntity {

    public KeepXpOnRespawnMixin(World world, BlockPos pos, float yaw, GameProfile gameProfile) {
        super(world, pos, yaw, gameProfile);
    }

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

    @Override
    protected boolean shouldAlwaysDropXp() {
        return this.world.getGameRules().getBoolean(KeepXPOnDeathMod.dropXPOnDeath);
    }
}
