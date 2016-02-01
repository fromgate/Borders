package ru.nukkit.borders;

import cn.nukkit.level.Location;
import cn.nukkit.math.Vector2;

public class Border {

    String world;
    int minX;
    int minZ;
    int maxX;
    int maxZ;

    public Border (Location loc1,Location loc2){
        this (loc1.getLevel().getName(),loc1.getFloorX(),loc1.getFloorZ(), loc2.getFloorX(),loc2.getFloorZ());
    }

    public Border (String world, int x1, int z1, int x2, int z2){
        this.world = world;
        this.minX = Math.min(x1,x2);
        this.minZ = Math.min(z1,z2);
        this.maxX = Math.max(x1,x2);
        this.maxZ = Math.max(z1,z2);
    }

    public boolean isLocInBorder(Location loc){
        if (!world.equalsIgnoreCase(loc.getLevel().getName())) return false;
        if (loc.getFloorX()<minX) return false;
        if (loc.getFloorX()>maxX) return false;
        if (loc.getFloorZ()<minZ) return false;
        return loc.getFloorZ()<=maxZ;
    }

    @Override
    public String toString(){
        return new StringBuilder(world).append(", ").append(minX).append(",").append(minZ).append(", ").append(maxX).append(",").append(maxZ).toString();
    }

    public String toPrintString(){
        return new StringBuilder("[").append(world).append("] ").append(minX).append(",").append(minZ).append(", ").append(maxX).append(",").append(maxZ).toString();
    }

    public double distanceTo (Location loc){
        if (isLocInBorder(loc)) return  0;
        int x = loc.getFloorX();
        int z = loc.getFloorZ();
        double a = distPointToLine(x,z,minX,minZ,maxX,minZ);
        double b = distPointToLine(x,z,minX,minZ,minX,maxZ);
        double c = distPointToLine(x,z,minX,maxZ,maxX,maxZ);
        double d = distPointToLine(x,z,maxX,maxZ,maxX,minZ);
        return Math.min(a,Math.min(b,Math.min(c,d)));
    }


    private double distPointToPoint(double ax, double az, double bx, double bz){
        return new Vector2(ax,az).distanceSquared(new Vector2(bx,bz));

    }
    private double distPointToLine(double ox, double oz, double ax, double az, double bx, double bz){
        double a1, a3, b1, b3, a, b, c;

        a= distPointToPoint(ox,oz,ax,az);
        b= distPointToPoint(ox,oz,bx,bz);
        c= distPointToPoint(ax,az,bx,bz);

        if(a>=b+c) return Math.sqrt(b);
        if(b>=a+c) return Math.sqrt(a);

        a1=ax-ox; a3=az-oz;
        b1=bx-ox; b3=bz-oz;

        return Math.sqrt(distPointToPoint(-(a1*b3-a3*b1),0,0,0)/c);
    }


}
