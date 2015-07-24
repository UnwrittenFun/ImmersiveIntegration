package unwrittenfun.minecraft.immersiveintegration.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import unwrittenfun.minecraft.immersiveintegration.ImmersiveIntegration;
import unwrittenfun.minecraft.immersiveintegration.tiles.TileItemRobin;

public class BlockItemRobin extends BlockContainer {
  public BlockItemRobin(String key) {
    super(Material.iron);
    setBlockName(key);
    setBlockTextureName(key);
    setCreativeTab(ImmersiveIntegration.iiCreativeTab);
    setHardness(2.5f);
  }

  @Override
  public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
    return new TileItemRobin();
  }

  @Override
  public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
    if (!world.isRemote) {
      TileEntity tileEntity = world.getTileEntity(x, y, z);
      if (tileEntity instanceof TileItemRobin) {
        TileItemRobin itemRobin = (TileItemRobin) tileEntity;
        ItemStack held = player.getHeldItem();
        int count = held == null ? 0 : held.stackSize;
        itemRobin.setSideCount(side, count);
        player.addChatComponentMessage(new ChatComponentText(StatCollector.translateToLocal("immersiveintegration.chat.itemRobin.countSet") + " " + count));
      }
    }
    return true;
  }
}
