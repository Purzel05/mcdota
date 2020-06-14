package de.prentl.firsttestproject;

import net.minecraft.server.v1_15_R1.Vec3D;

import java.util.ArrayList;
import java.util.List;

public abstract class McdMap {

    public static Vec3D getPlayerLocation(Side side) {
        if (side.equals(Side.BLUE)) {
            return new Vec3D(2.0D, 5.0D, 2.0D);
        } else if (side.equals(Side.YELLOW)) {
            return new Vec3D(98.0D, 5.0D, 98.0D);
        } else {
            return null;
        }
    }

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
                    return getLocation(12.0D, 7.0D, 12.0D, 2.0D);
                } else if (type.equals(TowerLocation.MID)) {
                    return getLocation(39.0D, 6.0D, 39.0D, 11.0D);
                } else if (type.equals(TowerLocation.RIVER)) {
                    return getLocation(81.0D, 9.0D, 77.0D, 13.0D);
                }
            } else if (lane.equals(Lane.CENTER)) {
                if (type.equals(TowerLocation.BASE)) {
                    return getLocation(10.0D, 12.0D, 12.0D, 10.0D);
                } else if (type.equals(TowerLocation.MID)) {
                    return getLocation(26.0D, 35.0D, 35.0D, 26.0D);
                } else if (type.equals(TowerLocation.RIVER)) {
                    return getLocation(43.0D, 49.0D, 49.0D, 43.0D);
                }
            } else if (lane.equals(Lane.RIGHT)) {
                if (type.equals(TowerLocation.BASE)) {
                    return getLocation(2.0D, 12.0D, 7.0D, 12.0D);
                } else if (type.equals(TowerLocation.MID)) {
                    return getLocation(6.0D, 36.0D, 11.0D, 36.0D);
                } else if (type.equals(TowerLocation.RIVER)) {
                    return getLocation(11.0D, 84.0D, 15.0D, 78.0D);
                }
            }
        } else if (side.equals(Side.YELLOW)) {
            if (lane.equals(Lane.LEFT)) {
                if (type.equals(TowerLocation.BASE)) {
                    return getLocation(88.0D, 93.0D, 88.0D, 98.0D);
               } else if (type.equals(TowerLocation.MID)) {
                    return getLocation(61.0D, 89.0D, 61.0D, 94.0D);
                } else if (type.equals(TowerLocation.RIVER)) {
                    return getLocation(19.0D, 91.0D, 24.0D, 87.0D);
                }
            } else if (lane.equals(Lane.CENTER)) {
                if (type.equals(TowerLocation.BASE)) {
                    return getLocation(88.0D, 90.0D, 90.0D, 88.0D);
                } else if (type.equals(TowerLocation.MID)) {
                    return getLocation(65.0D, 74.0D, 74.0D, 65.0D);
                } else if (type.equals(TowerLocation.RIVER)) {
                    return getLocation(51.0D, 57.0D, 57.0D, 51.0D);
                }
            } else if (lane.equals(Lane.RIGHT)) {
                if (type.equals(TowerLocation.BASE)) {
                    return getLocation(88.0D, 93.0D, 88.0D, 98.0D);
                } else if (type.equals(TowerLocation.MID)) {
                    return getLocation(94.0D, 64.0D, 89.0D, 64.0D);
                } else if (type.equals(TowerLocation.RIVER)) {
                    return getLocation(89.0D, 16.0D, 85.0D, 22.0D);
                }
            }
        }
        return null;
    }

    private static List<Vec3D> getLocation(double x1, double z1, double x2, double z2) {
        List<Vec3D> list = new ArrayList<>();
        list.add(new Vec3D(x1, 4.0D, z1));
        list.add(new Vec3D(x2, 4.0D, z2));
        return list;
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
