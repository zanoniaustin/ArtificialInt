/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steepestdescent;

/**
 *
 * @author wmacevoy
 */
public interface RealParameters {
    int getRealParameterSize();
    String getRealParameterName(int index);
    int getRealParameterIndex(String name);
    double getRealParameterValue(int index);
    void setRealParameterValue(int index, double value);
}
