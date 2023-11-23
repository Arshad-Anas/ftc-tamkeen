// THIS IS OUR MAIN CODE!!!

package org.firstinspires.ftc.teamcode.opmodes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.TankDriveTrain;

import java.util.List;

@TeleOp(group = "21836 TeleOp")
public class TestHanger extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    DcMotor hangerMotor;



    @Override
    public void runOpMode() throws InterruptedException {

        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        hubs = hardwareMap.getAll(LynxModule.class);
        // Initialize your hardware components here
        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();

            if (gamepad1.atRest() == true) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            hangerMotor.setPower(gamepad1.left_trigger - gamepad1.right_trigger);
            myTelemetry.update();

        }


    }
}