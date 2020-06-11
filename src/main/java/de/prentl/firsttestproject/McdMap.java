package de.prentl.firsttestproject;

import net.minecraft.server.v1_15_R1.Vec3D;

public abstract class McdMap {

    public static Vec3D getLocation(Side side, Lane lane, LaneLocation location) {
        if (side.equals(Side.BLUE)) {
            if (lane.equals(Lane.LEFT)) {
                if (location.equals(LaneLocation.SPAWN)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else if (location.equals(LaneLocation.LANE)) {
                    return new Vec3D( 96.0D, 4.0D, 4.0D);
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
                    return new Vec3D( 4.0D, 4.0D, 96.0D);
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
                    return new Vec3D( 4.0D, 4.0D, 96.0D);
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
                    return new Vec3D( 96.0D, 4.0D, 4.0D);
                } else if (location.equals(LaneLocation.FINAL)) {
                    return new Vec3D(4.0D, 4.0D, 4.0D);
                } else {
                    return null;
                }
            }
        }
    }

    public static Vec3D getLocation(Side side, Lane lane, TowerLocation type) {
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
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    return new Vec3D(37.0D, 5.0D, 48.0D);
                } else {
                    return null;
                }
            } else {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    return null;
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
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    return null;
                } else {
                    return null;
                }
            } else {
                if (type.equals(TowerLocation.BASE)) {
                    return null;
                } else if (type.equals(TowerLocation.MID)) {
                    return null;
                } else if (type.equals(TowerLocation.RIVER)) {
                    return null;
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
