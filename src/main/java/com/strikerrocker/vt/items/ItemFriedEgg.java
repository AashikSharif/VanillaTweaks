package com.strikerrocker.vt.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;

import static com.strikerrocker.vt.main.vt.proxy;

/**
 * Created by thari on 23/07/2017.
 */
public class ItemFriedEgg extends ItemFood {
    public ItemFriedEgg() {
        super(3, 0.6f, false);
        setUnlocalizedName("friedegg");
        setRegistryName("friedegg");
        setCreativeTab(CreativeTabs.FOOD);
    }

    public void registerItemModel() {
        proxy.registerItemRenderer(this, 0, "friedegg");
    }
}
