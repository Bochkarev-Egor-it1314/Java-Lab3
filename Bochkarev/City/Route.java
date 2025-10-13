package ru.Bochkarev.City;

import java.util.*;

/**
 * ru.Bochkarev.City.Route — сущность маршрута между start и end.
 * Инициализация: start и end не могут быть null.
 * setStart/setEnd проверяют на null и выполняются за O(1).
 * getRoute() возвращает массив ru.Bochkarev.City.NewCity[], представляющий последовательность городов
 * от start до end (включая оба). Если пути нет — возвращается пустой массив.
 *
 * Алгоритм поиска: BFS (минимальное число вершин / переходов).
 */
public class Route {
    private NewCity start;
    private NewCity end;

    public Route(NewCity start, NewCity end) {
        if (start == null || end == null)
            throw new IllegalArgumentException("Start and end cities must not be null");
        this.start = start;
        this.end = end;
    }

    public NewCity getStart() {
        return start;
    }

    public NewCity getEnd() {
        return end;
    }

    public void setStart(NewCity start) {
        if (start == null) throw new IllegalArgumentException("Start city must not be null");
        this.start = start;
    }

    public void setEnd(NewCity end) {
        if (end == null) throw new IllegalArgumentException("End city must not be null");
        this.end = end;
    }

    /**
     * Возвращает маршрут как массив вершин (включая start и end).
     * Путь строится BFS — минимальное число переходов.
     * Если путь не найден — возвращает пустой массив.
     */
    public NewCity[] getRoute() {
        // Быстрые случаи
        if (start.equals(end)) {
            return new NewCity[]{start};
        }

        // BFS
        Queue<NewCity> q = new LinkedList<>();
        Map<NewCity, NewCity> prev = new HashMap<>(); // для восстановления пути: prev.get(v) = predecessor
        Set<NewCity> visited = new HashSet<>();

        q.add(start);
        visited.add(start);

        boolean found = false;

        outer:
        while (!q.isEmpty()) {
            NewCity cur = q.poll();
            for (NewCity nb : cur.getPath().keySet()) {
                if (!visited.contains(nb)) {
                    visited.add(nb);
                    prev.put(nb, cur);
                    if (nb.equals(end)) {
                        found = true;
                        break outer; // нашли — выходим из обоих циклов
                    }
                    q.add(nb);
                }
            }
        }

        if (!found) {
            return new NewCity[0]; // пустой массив, если путь не существует
        }

        // Восстанавливаем путь от end к start по prev, затем переворачиваем
        List<NewCity> reversed = new ArrayList<>();
        NewCity cur = end;
        while (cur != null) {
            reversed.add(cur);
            cur = prev.get(cur);
        }
        Collections.reverse(reversed);
        return reversed.toArray(new NewCity[0]);
    }

    @Override
    public String toString() {
        NewCity[] route = getRoute();
        if (route.length == 0) return "(no route)";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < route.length; i++) {
            if (i > 0) sb.append(" -> ");
            sb.append(route[i].getName());
        }
        return sb.toString();
    }
}
