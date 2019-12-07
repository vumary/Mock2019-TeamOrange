/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CascadeLift extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private TalonSRX liftMotor;
  private DigitalInput gate0, gate1, gate2;
  private ArrayList<Boolean> photoGates;
  private int currentPosition, desiredPosition;
  
  public CascadeLift(){
    
    liftMotor = new TalonSRX(RobotMap.liftMotorPort);
    gate0 = new DigitalInput(RobotMap.gate0Port);
    gate1 = new DigitalInput(RobotMap.gate1Port);
    gate2 = new DigitalInput(RobotMap.gate2Port);
    currentPosition = 0;

  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void setLiftPwr(double pwr){
    liftMotor.set(ControlMode.PercentOutput, pwr);
  }

  public ArrayList<Boolean> getPhotoGates(){
    ArrayList<Boolean> temp = new ArrayList<>(3);

    temp.set(0, gate0.get());
    temp.set(1, gate1.get());
    temp.set(2, gate2.get());

    return temp;
  }

  public void setPhotoGates(ArrayList<Boolean> n){
    photoGates = n;
  }

  public int getCurrentPosition(){
    return currentPosition;
  }

  public void setDesiredPosition(int n){
    desiredPosition = n;
  }

  public int getDesiredPosition(){
    return desiredPosition;
  }

  public int getDifference(){
    return (currentPosition - desiredPosition);
  }

  public void updateCurrentPosition(){
    
    for(int i = 0; i < photoGates.size(); i++){
      
      if( photoGates.get(i) ){
        currentPosition = i;
      }

    }

  }

}
