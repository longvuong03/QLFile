package org.longvuong.qlfile.data;

import org.longvuong.qlfile.data.model.Shape;

public interface IShapeManager {
    public void add(Shape shape);
    public void remove(Shape shape);
    public void sort();
    public Shape findMax();
}
