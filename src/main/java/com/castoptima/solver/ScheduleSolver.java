package com.castoptima.solver;

import com.castoptima.model.Actor;
import com.castoptima.model.Scene;
import java.util.List;
import java.util.Map;

public interface ScheduleSolver {
    List<Scene> solve(List<Scene> scenes, Map<String, Actor> actors);
}