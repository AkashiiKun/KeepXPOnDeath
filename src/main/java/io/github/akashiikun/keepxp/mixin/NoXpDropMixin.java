package io.github.akashiikun.keepxp.mixin;

import io.github.akashiikun.keepxp.KeepXPOnDeathMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;

@Mixin(PlayerEntity.class)
public abstract class NoXpDropMixin extends LivingEntity {

    protected NoXpDropMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "getXpToDrop", at = @At("RETURN"), cancellable = true)
    private void noXpToDrop(CallbackInfoReturnable<Integer> cir) {
        if (!this.world.getGameRules().getBoolean(KeepXPOnDeathMod.dropXPOnDeath)) {
            cir.setReturnValue(0);
        }
    }
}
