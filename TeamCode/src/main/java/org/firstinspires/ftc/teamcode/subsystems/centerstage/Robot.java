package org.firstinspires.ftc.teamcode.subsystems.centerstage;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.drivetrains.MecanumDrivetrain;

import java.util.List;

public final class Robot {

    public final MecanumDrivetrain drivetrain;

    private final List<LynxModule> revHubs;

    public static double maxVoltage = 13;

    public Robot(HardwareMap hardwareMap) {

        revHubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : revHubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        drivetrain = new MecanumDrivetrain(hardwareMap);
    }

    public void readSensors(){
        for (LynxModule hub : revHubs) hub.clearBulkCache();

    }

    public void interrupt(){

        drivetrain.imu.interrupt();
    }
}
