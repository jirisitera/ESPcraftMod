package espcraft.procedures;

import org.checkerframework.checker.units.qual.s;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.advancements.Advancement;

import java.util.Map;
import java.util.Iterator;
import java.util.Comparator;

import espcraft.network.EspcraftModVariables;

import espcraft.init.EspcraftModBlocks;

public class UpdateBlockProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((EspcraftModVariables.MapVariables.get(world).Value).length() == 11) {
			if ((EspcraftModVariables.MapVariables.get(world).Value.substring((int) 0, (int) 4)).equals(new Object() {
				public String getValue(LevelAccessor world, BlockPos pos, String tag) {
					BlockEntity blockEntity = world.getBlockEntity(pos);
					if (blockEntity != null)
						return blockEntity.getTileData().getString(tag);
					return "";
				}
			}.getValue(world, new BlockPos(x, y, z), "Name"))) {
				if ((EspcraftModVariables.MapVariables.get(world).Value.substring((int) 4, (int) 9)).equals(new Object() {
					public String getValue(LevelAccessor world, BlockPos pos, String tag) {
						BlockEntity blockEntity = world.getBlockEntity(pos);
						if (blockEntity != null)
							return blockEntity.getTileData().getString(tag);
						return "";
					}
				}.getValue(world, new BlockPos(x, y, z), "Channel"))) {
					if (new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(EspcraftModVariables.MapVariables.get(world).Value.substring((int) 9, (int) 11)) != new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(x, y, z), "Power")) {
						if (!world.isClientSide()) {
							BlockPos _bp = new BlockPos(x, y, z);
							BlockEntity _blockEntity = world.getBlockEntity(_bp);
							BlockState _bs = world.getBlockState(_bp);
							if (_blockEntity != null)
								_blockEntity.getTileData().putDouble("Power", new Object() {
									double convert(String s) {
										try {
											return Double.parseDouble(s.trim());
										} catch (Exception e) {
										}
										return 0;
									}
								}.convert(EspcraftModVariables.MapVariables.get(world).Value.substring((int) 9, (int) 11)));
							if (world instanceof Level _level)
								_level.sendBlockUpdated(_bp, _bs, _bs, 3);
						}
						EspcraftModVariables.MapVariables.get(world).Value = "";
						EspcraftModVariables.MapVariables.get(world).syncData(world);
						if (world instanceof Level _level)
							_level.updateNeighborsAt(new BlockPos(x, y, z), _level.getBlockState(new BlockPos(x, y, z)).getBlock());
						if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).stream()
								.sorted(new Object() {
									Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
										return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
									}
								}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof ServerPlayer _player) {
							Advancement _adv = _player.server.getAdvancements().getAdvancement(new ResourceLocation("espcraft:espcraft_5"));
							AdvancementProgress _ap = _player.getAdvancements().getOrStartProgress(_adv);
							if (!_ap.isDone()) {
								Iterator _iterator = _ap.getRemainingCriteria().iterator();
								while (_iterator.hasNext())
									_player.getAdvancements().award(_adv, (String) _iterator.next());
							}
						}
						if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == EspcraftModBlocks.BLOCKRECEIVEROFF.get()) {
							if (new Object() {
								public double getValue(LevelAccessor world, BlockPos pos, String tag) {
									BlockEntity blockEntity = world.getBlockEntity(pos);
									if (blockEntity != null)
										return blockEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos(x, y, z), "Power") != 0) {
								{
									BlockPos _bp = new BlockPos(x, y, z);
									BlockState _bs = EspcraftModBlocks.BLOCKRECEIVERON.get().defaultBlockState();
									BlockState _bso = world.getBlockState(_bp);
									for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
										Property _property = _bs.getBlock().getStateDefinition().getProperty(entry.getKey().getName());
										if (_property != null && _bs.getValue(_property) != null)
											try {
												_bs = _bs.setValue(_property, (Comparable) entry.getValue());
											} catch (Exception e) {
											}
									}
									BlockEntity _be = world.getBlockEntity(_bp);
									CompoundTag _bnbt = null;
									if (_be != null) {
										_bnbt = _be.saveWithFullMetadata();
										_be.setRemoved();
									}
									world.setBlock(_bp, _bs, 3);
									if (_bnbt != null) {
										_be = world.getBlockEntity(_bp);
										if (_be != null) {
											try {
												_be.load(_bnbt);
											} catch (Exception ignored) {
											}
										}
									}
								}
								if (world instanceof Level _level)
									_level.updateNeighborsAt(new BlockPos(x, y, z), _level.getBlockState(new BlockPos(x, y, z)).getBlock());
							}
						}
					}
				}
			}
		}
	}
}
