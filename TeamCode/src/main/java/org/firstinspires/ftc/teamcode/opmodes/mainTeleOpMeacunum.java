package org.firstinspires.ftc.teamcode.opmodes;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.centerstage.Robot;

@TeleOp(group = "23925 TeleOp")
public class mainTeleOpMeacunum extends LinearOpMode {

    MultipleTelemetry mTelemetry;
    GamepadEx gamepadEx1, gamepadEx2;
    Robot robot;
    boolean servoPositionIsZero = false;
    DcMotor hangerMotor;
    DcMotor armMotorTilt;
    DcMotor armMotorSpin;
    Servo clawServo1, clawServo2;
    Servo planeServo, doorServo1, doorServo2;


    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        armMotorTilt = hardwareMap.dcMotor.get("armMotorTilt");
        //armMotorSpin = hardwareMap.dcMotor.get("armMotorSpin");
//        planeServo = hardwareMap.servo.get("planeServo");
        clawServo1 = hardwareMap.servo.get("clawServo1");
        clawServo2 = hardwareMap.servo.get("clawServo2");
//        doorServo1 = hardwareMap.get(Servo.class, "doorServo1");
//        doorServo2 = hardwareMap.get(Servo.class, "doorServo2");
//
//        planeServo.setPosition(1);
//
//        doorServo1.setPosition(0);
//        //doorServo2.setPosition(0);
//
        waitForStart();
//
//        //doorServo2.setDirection(Servo.Direction.REVERSE);

        // Control loop:
        while (opModeIsActive()) {
            robot.readSensors();
            // Read sensors + gamepads:
            gamepadEx1.readButtons();
            gamepadEx2.readButtons();

            //For the hanger
            if (gamepad2.atRest()) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                armMotorTilt.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                //armMotorSpin.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }

            telemetry.addData("motor", hangerMotor.getCurrentPosition());
            //if (hangerMotor.getCurrentPosition() < 15) {
                //hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            //} else {
                hangerMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            //}
            //armMotorSpin.setPower(gamepad2.right_stick_x);
            armMotorTilt.setPower((gamepad2.left_stick_y)*0.5);
//
//            // For the plane servo
//            if (gamepad2.a) {
//
//                // Toggle the servo position
//                if (servoPositionIsZero) {
//                    planeServo.setPosition(1);
//                } else if (gamepad2.a) {
//                    planeServo.setPosition(0);
//                }
//
//                servoPositionIsZero = !servoPositionIsZero; // Toggle the state
//                sleep(500); // Add a delay to prevent rapid toggling
//            }

            // for the claw
            if (gamepad2.a && (clawServo1.getPosition() != 0.0 || clawServo2.getPosition() != 0.0)) {
                clawServo1.setPosition(0.0);
                //clawServo2.setPosition(0.0);


            } else if (gamepad2.a) {
                clawServo1.setDirection(Servo.Direction.REVERSE);
                clawServo1.setPosition(.5);
                //clawServo2.setDirection(Servo.Direction.REVERSE);
                //clawServo2.setPosition(.5);
            } //

            // Door Servo
//            if (gamepad1.a) {
//                if (doorServo1.getPosition() == 0) {
//                    setPosition(0.475);
//                } else {
//                    setPosition(0);
//                }
//            }


            // Field-centric drive dt with control stick inputs:
            robot.drivetrain.run(
                    gamepadEx1.getLeftX(),
                    gamepadEx1.getLeftY(),
                    gamepadEx1.getRightX()
            );

            mTelemetry.update();
            telemetry.update();
            robot.interrupt();
        }
    }

    public void setPosition(double position) {
        doorServo1.setPosition(position);
        //doorServo2.setPosition(1 - position);
    }
}
