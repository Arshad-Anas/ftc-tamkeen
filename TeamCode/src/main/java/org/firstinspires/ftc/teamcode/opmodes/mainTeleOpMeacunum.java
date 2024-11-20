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
    Servo clawServo1, clawServo2, upDownServo1, upDownServo2;

    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        clawServo1 = hardwareMap.servo.get("clawServo1");
        clawServo2 = hardwareMap.servo.get("clawServo2");
        upDownServo1 = hardwareMap.servo.get("upDownServo1");
        upDownServo2 = hardwareMap.servo.get("upDownServo2");

        robot.readSensors();
        // Read sensors + gamepads:
        gamepadEx1.readButtons();
        gamepadEx2.readButtons();

        waitForStart();

        // Control loop:
        while (opModeIsActive()) {

            //For the upDownServo to move
            if (gamepad2.a) {
                upDownServo1.setDirection(Servo.Direction.FORWARD);
                upDownServo1.setPosition(0.0);
                upDownServo2.setDirection(Servo.Direction.REVERSE);
                upDownServo2.setPosition(0.0);
            }

            if (gamepad2.b) {
                upDownServo1.setDirection(Servo.Direction.REVERSE);
                upDownServo1.setPosition(0.5);
                upDownServo2.setDirection(Servo.Direction.FORWARD);
                upDownServo2.setPosition(0.5);
            }

            if (gamepad2.dpad_up) {
                upDownServo1.setDirection(Servo.Direction.REVERSE);
                upDownServo1.setPosition(0.9);
                upDownServo2.setDirection(Servo.Direction.FORWARD);
                upDownServo2.setPosition(0.9);
            }
            //robot.interrupt();

            // For the claw to open and close
            if (gamepad2.x) {
                clawServo1.setDirection(Servo.Direction.FORWARD);
                clawServo1.setPosition(0.0);
                clawServo2.setDirection(Servo.Direction.REVERSE);
                clawServo2.setPosition(0.0);
            } else if (gamepad2.y) {
                clawServo1.setDirection(Servo.Direction.REVERSE);
                clawServo1.setPosition(.5);
                clawServo2.setDirection(Servo.Direction.FORWARD);
                clawServo2.setPosition(.5);
            }
            //robot.interrupt();

            //For the hanger
            if (gamepad2.atRest()) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            //robot.interrupt();

            telemetry.addData("motor", upDownServo1.getPosition());

            hangerMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);
            //robot.interrupt();

//            if (gamepad2.a && (clawServo1.getPosition() != 0.0 || clawServo2.getPosition() != 0.0)) {
//                clawServo1.setPosition(0.0);
//                clawServo2.setPosition(0.0);
//            } else if (gamepad2.a) {
//                clawServo1.setDirection(Servo.Direction.REVERSE);
//                clawServo1.setPosition(.25);
//                clawServo2.setDirection(Servo.Direction.FORWARD);
//                clawServo2.setPosition(.25);
//            }

//            // For the upDownServo to move
//            if (gamepad2.b && (upDownServo1.getPosition() != 0.0 || upDownServo2.getPosition() != 0.0)) {
//                upDownServo1.setDirection(Servo.Direction.FORWARD);
//                upDownServo1.setPosition(0.0);
//                upDownServo2.setDirection(Servo.Direction.REVERSE);
//                upDownServo2.setPosition(0.0);
//            } else if (gamepad2.b) {
//                upDownServo1.setDirection(Servo.Direction.REVERSE);
//                upDownServo1.setPosition(.5);
//                upDownServo2.setDirection(Servo.Direction.FORWARD);
//                upDownServo2.setPosition(.5);
//            }

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
        //doorServo1.setPosition(position);
        //doorServo2.setPosition(1 - position);
    }
}
