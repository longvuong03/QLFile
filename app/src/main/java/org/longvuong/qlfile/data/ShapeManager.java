package org.longvuong.qlfile.data;

import org.longvuong.qlfile.data.model.Shape;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShapeManager implements IShapeManager{
    private List<Shape> shapeList = new ArrayList<Shape>();
    @Override
    public void add(Shape shape) {
        shapeList.add(shape);
    }

    @Override
    public void remove(Shape shape) {
        shapeList.remove(shape);
    }

    @Override
    public void sort() {
        shapeList.sort(new Comparator<Shape>() {
            @Override
            public int compare(Shape o1, Shape o2) {
                return o1.chuVi() > o2.chuVi() ? 1 : -1;
            }
        });
    }

    @Override
    public Shape findMax() {
        return null;
    }
}
