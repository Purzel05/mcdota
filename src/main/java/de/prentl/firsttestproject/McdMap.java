package de.prentl.firsttestproject;

import net.minecraft.server.v1_15_R1.Vec3D;

import java.util.ArrayList;
import java.util.List;

public abstract class McdMap {

    public static Vec3D getLocation(Side side, Lane lane, LaneLocation location) {
        if (side.equals(Side.BLUE)) {
            if (lane.equals(Lane.LEFT)) {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 83.0D, 4.0D, 16.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(96.0D, 4.0D, 96.0D);
                } else {
                    return null;
                }
            } else if (lane.equals(Lane.CENTER)) {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 50.0D, 4.0D, 50.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(96.0D, 4.0D, 96.0D);
                } else {
                    return null;
                }
            } else {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 17.0D, 4.0D, 84.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(96.0D, 4.0D, 96.0D);
                } else {
                    return null;
                }
            }
        } else {
            if (lane.equals(Lane.LEFT)) {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(96.0D, 4.0D, 96.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 17.0D, 4.0D, 84.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else {
                    return null;
                }
            } else if (lane.equals(Lane.CENTER)) {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(96.0D, 4.0D, 96.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 50.0D, 4.0D, 50.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else {
                    return null;
                }
            } else {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(96.0D, 4.0D, 96.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 83.0D, 4.0D, 16.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else {
                    return null;
                }
            }
        }
    }

    public static List<Vec3D> getLocation(Side side, Lane lane, TowerLocation type) {
        if (side.equals(Side.BLUE)) {
            if (lane.equals(Lane.LEFT)) {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    return null;
                } else {
                    return null;
                }
            } else if (lane.equals(Lane.CENTER)) {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    List<Vec3D> list = new ArrayList<>();
                    list.add(new Vec3D(26.0D, 5.0D, 35.0D));
                    list.add(new Vec3D(35.0D, 5.0D, 26.0D));
                    return list;
                } else if (type.equals(TowerLocation.RIVER)) {
                    List<Vec3D> list = new ArrayList<>();
                    list.add(new Vec3D(43.0D, 5.0D, 49.0D));
                    list.add(new Vec3D(49.0D, 5.0D, 43.0D));
                    return list;
                } else {
                    return null;
                }
            } else {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    List<Vec3D> list = new ArrayList<>();
                    list.add(new Vec3D(11.0D, 5.0D, 84.0D));
                    list.add(new Vec3D(15.0D, 5.0D, 78.0D));
                    return list;
                } else {
                    return null;
                }
            }
        } else {
            if (lane.equals(Lane.LEFT)) {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    return null;
                } else {
                    return null;
                }
            } else if (lane.equals(Lane.CENTER)) {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    List<Vec3D> list = new ArrayList<>();
                    list.add(new Vec3D(65.0D, 5.0D, 74.0D));
                    list.add(new Vec3D(74.0D, 5.0D, 65.0D));
                    return list;
                } else if (type.equals(TowerLocation.RIVER)) {
                    List<Vec3D> list = new ArrayList<>();
                    list.add(new Vec3D(51.0D, 5.0D, 57.0D));
                    list.add(new Vec3D(57.0D, 5.0D, 51.0D));
                    return list;
                } else {
                    return null;
                }
            } else {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    List<Vec3D> list = new ArrayList<>();
                    list.add(new Vec3D(89.0D, 5.0D, 16.0D));
                    list.add(new Vec3D(85.0D, 5.0D, 22.0D));
                    return list;
                } else {
                    return null;
                }
            }
        }
    }

    public enum Side {
        BLUE,
        YELLOW
    }

    public enum Lane {
        LEFT,
        CENTER,
        RIGHT
    }

    public enum LaneLocation {
        SPAWN,
        LANE,
        FINAL
    }

    public enum TowerLocation {
        BASE,
        MID,
        RIVER
    }
}
