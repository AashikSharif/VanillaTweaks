package com.strikerrocker.vt.items;

import com.mojang.realmsclient.gui.ChatFormatting;
import com.strikerrocker.vt.entities.EntityDynamite;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Dynamite item to go along-side dynamite entity
 */
public class ItemDynamite extends ItemBase {

    private String name;

    public ItemDynamite(String name) {
        super(name);
        this.name = name;
        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxStackSize(16);
        setUnlocalizedName(name);
    }

    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {

        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (!playerIn.isCreative()) {
            itemstack.shrink(1);
        }

        worldIn.playSound(null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));


        if (!worldIn.isRemote) {
            EntityDynamite dynamite = new EntityDynamite(worldIn, playerIn);
            dynamite.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0, 1.5F, 0);
            playerIn.getEntityWorld().spawnEntity(dynamite);
        }
        playerIn.addStat(StatList.getObjectUseStats(this));
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);

    }


    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, java.util.List<String> info, boolean par4) {
        info.add(ChatFormatting.RED + "This doesn't work.");
    }

}
