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
public class TeleOpFIX3 extends LinearOpMode {

    MultipleTelemetry myTelemetry;
    List<LynxModule> hubs;

    Servo yourServo;
    boolean servoPositionIsZero = true;
    TankDriveTrain drive;
    DcMotor hangerMotor;


    @Override
    public void runOpMode() throws InterruptedException {

        myTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        GamepadEx Gamepad1 = new GamepadEx(gamepad1);
        GamepadEx Gamepad2 = new GamepadEx(gamepad2);
        hubs = hardwareMap.getAll(LynxModule.class);
        drive = new TankDriveTrain(hardwareMap);
        // Initialize your hardware components here
        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        yourServo = hardwareMap.servo.get("your_servo");

        // Set the initial position of the servo
        yourServo.setPosition(0);  // 0 degrees

        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();

            //driving
            drive.setPower(gamepad1.right_stick_y, gamepad1.left_stick_x);


            if (gamepad2.atRest() == true) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            hangerMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);

            // Check if the "A" button on gamepad2 is pressed
            if (gamepad2.a) {

                // Toggle the servo position
                if (servoPositionIsZero) {
                    yourServo.setPosition(45);  // This value will not work for the plan to launch (0.25)
                } else {
                    yourServo.setPosition(0.0);  // 0 degrees
                }
                servoPositionIsZero = !servoPositionIsZero; // Toggle the state
                sleep(500); // Add a delay to prevent rapid toggling
            }
            myTelemetry.update();
        }
    }
}
