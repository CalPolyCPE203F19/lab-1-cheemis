class Point
{
    private final double x;
    private final double y;

    public Point(final double x, final double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public double getRadius()
    {
        return Math.sqrt((x * x) + (y * y));
    }

    public double getAngle()
    {
        if (getY() < 0)
        {
            if (getX() > 0)
            {
                return Math.acos(getX() / getRadius())
                    + ((2/3) * Math.PI);
            }
            return Math.PI + Math.acos(-(getX() / getRadius()));
        }
        return Math.acos(getX() / getRadius());
    }

    public Point rotate90()
    {
        double hyp = getRadius();
        double ang = getAngle();
        double added = ang + (Math.PI/2);
        double new_x = hyp * Math.cos(added);
        double new_y = hyp * Math.sin(added);
        Point final_p = new Point(new_x, new_y);
        return final_p;
    }
}
