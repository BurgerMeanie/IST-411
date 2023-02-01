/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package noaa.web.services;

/**
 *
 * @author micha
 */
public class ResultSet {
    private int offset;
    private int count;
    private int limit;
    
    public void setOffset(int offset){
        this.offset = offset;
    }
    
    public int getOffset(){
        return offset;
    }
    
    public void setCount(int count){
        this.count = count;
    }
    
    public int getCount(){
        return count;
    }
    
    public void setLimit(int limit){
        this.limit = limit;
    }
    
    public int getLimit(){
        return limit;
    }
}
