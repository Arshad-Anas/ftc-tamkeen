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

    Servo planeServo, doorServo1, doorServo2;
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
        planeServo = hardwareMap.servo.get("your_servo");
        doorServo1 = hardwareMap.get(Servo.class, "servo1");
        doorServo2 = hardwareMap.get(Servo.class, "servo2");

        // Set the initial position of the servo
        planeServo.setPosition(0);  // 0 degrees

        // set up the variables for the door
        doorServo1.setPosition(0);
        doorServo2.setPosition(0);
        boolean position = true;

        for (LynxModule hub : hubs) hub.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);

        waitForStart();

        while (opModeIsActive()) {
            for (LynxModule hub : hubs) hub.clearBulkCache();

            //driving
            drive.setPower(gamepad1.right_stick_y, gamepad1.left_stick_x);

            // To control the hanging arm
            if (gamepad2.atRest() == true) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            hangerMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);

            // Check if the "A" button on gamepad2 is pressed
            // This is to launch the plane
            if (gamepad2.a) {

                // Toggle the servo position
                if (servoPositionIsZero) {
                    planeServo.setPosition(45);  // This value will not work for the plan to launch (0.25)
                } else {
                    planeServo.setPosition(0.0);  // 0 degrees
                }
                servoPositionIsZero = !servoPositionIsZero; // Toggle the state
                sleep(500); // Add a delay to prevent rapid toggling
            }

            // To move the arm on the front of the Robot
            if (gamepad1.a && position) {
                if (doorServo1.getPosition() == 0) {
                    setPosition(.475);
                } else {
                    setPosition(0);
                }
                position = false;
            }

            if (!gamepad1.a) {
                position = true;
            }

            myTelemetry.update();
        }
    }

    public void setPosition(double position) {
        doorServo1.setPosition(position);
        doorServo2.setPosition(1-position);
    }
}
