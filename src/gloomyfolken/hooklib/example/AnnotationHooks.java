package gloomyfolken.hooklib.example;

import gloomyfolken.hooklib.asm.Hook;
import gloomyfolken.hooklib.asm.Hook.ReturnValue;
import gloomyfolken.hooklib.asm.ReturnCondition;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.ForgeHooks;

public class AnnotationHooks {

    /**
     * Цель: при каждом ресайзе окна выводить в консоль новый размер
     */
    @Hook
    public static void resize(Minecraft mc, int x, int y){
        System.out.println("ResizePRE1, x=" + x + ", y=" + y);
    }

    /**
     * Цель: уменьшить вдвое показатели брони у всех игроков.
     * P.S: фордж перехватывает получение показателя брони, ну а мы перехватим перехватчик :D
     */
    @Hook(injectOnExit = true, returnCondition = ReturnCondition.ALWAYS)
    public static int getTotalArmorValue(ForgeHooks fh, EntityPlayer player, @ReturnValue int returnValue) {
        return returnValue/2;
    }

    /**
     * Цель: запретить возможность телепортироваться в ад и обратно чаще, чем раз в пять секунд.
     */
    @Hook(returnCondition = ReturnCondition.ALWAYS, intReturnConstant = 100)
    public static void getPortalCooldown(EntityPlayer player) {
        // а делать ничего и не нужно
    }
}
