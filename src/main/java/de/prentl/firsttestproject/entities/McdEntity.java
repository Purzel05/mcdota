package de.prentl.firsttestproject.entities;

import de.prentl.firsttestproject.McdMap;

public interface McdEntity {
    McdMap.Side getSide();
    void updateGoalsAndTargets();
}
