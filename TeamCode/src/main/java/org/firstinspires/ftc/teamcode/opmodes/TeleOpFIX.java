package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import java.util.List;

@TeleOp(group = "21836 TeleOp")
public class TeleOpFIX extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    @Override
    public void runOpMode() throws InterruptedException {

        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        DcMotor leftMotor = hardwareMap.get(DcMotor.class, "left");
        DcMotor rightMotor = hardwareMap.get(DcMotor.class, "rightd");
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        hubs = hardwareMap.getAll(LynxModule.class);
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();
            double leftPower = Gamepad1.getLeftY();
            double rightPower = Gamepad1.getRightY();
            leftMotor.setPower(leftPower);
            rightMotor.setPower(rightPower);
            myTelemetry.update();
        }
    }
}
