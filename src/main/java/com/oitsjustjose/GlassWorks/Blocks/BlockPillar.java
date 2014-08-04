package com.oitsjustjose.GlassWorks.Blocks;

import com.oitsjustjose.GlassWorks.GlassWorks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import java.util.List;

public class BlockPillar extends Block
{
	public static IIcon[] PillarTextures = new IIcon[4];

	public BlockPillar()
	{
		super(Material.rock);
		setCreativeTab(GlassWorks.tabGlass);
		setResistance(4.0F);
		setHardness(2.0F);
		setStepSound(Block.soundTypeStone);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		int meta = world.getBlockMetadata(x, y, z);
		
		if(side == 0 || side == 1)
		{
			return PillarTextures[0];
		}
		
		if(side != 1 || side != 2)
		{
			if(world.getBlock(x, y + 1, z) == this && world.getBlockMetadata(x, y + 1, z) == meta)
			{
				if(world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == meta)
					return PillarTextures[2];
				else
					return PillarTextures[3];
			}
			else if(world.getBlock(x, y - 1, z) == this && world.getBlockMetadata(x, y - 1, z) == meta)
				return PillarTextures[1];
		}
		
			return PillarTextures[0];
	}

	@Override
	@SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
		return PillarTextures[0];
    }
    
    @Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegistry)
	{
		for(int i = 0; i < 4; i++)
			PillarTextures[i] = iconRegistry.registerIcon("GlassWorks:CTM/Pillar/" + i);
	}

    @Override
    public int damageDropped(int par1)
    {
    	return par1;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getBlockColor()
    {
    	return this.getRenderColor(Block.getIdFromBlock(this));
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int getRenderColor(int par1)
    {
    	switch(par1)
    	{
    	case 0:return 2960685;
    	case 1:return 8947848;
    	case 2:return 16777215;
    	default:return 0;
    	}
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public int colorMultiplier(IBlockAccess world, int x, int y, int z)
    {
    	int meta = world.getBlockMetadata(x, y, z);
    	switch(meta)	
    	{
    	case 0:return 2960685;
    	case 1:return 8947848;
    	case 2:return 16777215;
    	default:return 0;
    	}
    }
           
    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tab, List list)
    {
    	for(int meta=0;meta<3;meta++)
    		list.add(new ItemStack(item, 1, meta));
    }
}