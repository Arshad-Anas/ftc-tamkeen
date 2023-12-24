package org.firstinspires.ftc.teamcode.opmodes;

import static com.arcrobotics.ftclib.gamepad.GamepadKeys.Button.A;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.centerstage.Robot;

@TeleOp(group = "23925 TeleOp")
public class mainTeleOpMeacunum extends LinearOpMode {

    MultipleTelemetry mTelemetry;
    GamepadEx gamepadEx1, gamepadEx2;
    Robot robot;
    boolean servoPositionIsZero = false;
    DcMotor hangerMotor;
    Servo planeServo, clawServo;




    @Override
    public void runOpMode() throws InterruptedException {

        // Initialize multiple telemetry outputs:
        mTelemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        gamepadEx1 = new GamepadEx(gamepad1);
        gamepadEx2 = new GamepadEx(gamepad2);

        robot = new Robot(hardwareMap);

        hangerMotor = hardwareMap.dcMotor.get("hangerMotor");
        planeServo = hardwareMap.servo.get("planeServo");
        clawServo = hardwareMap.servo.get("clawServo");

        planeServo.setPosition(45);

        waitForStart();


        // Control loop:
        while (opModeIsActive()) {
            robot.readSensors();
            // Read sensors + gamepads:
            gamepadEx1.readButtons();
            gamepadEx2.readButtons();

            boolean aPressed = gamepadEx1.wasJustPressed(A); // this will only be true for one loop

            if (gamepad2.atRest() == true) {
                hangerMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
            hangerMotor.setPower(gamepad2.left_trigger - gamepad2.right_trigger);



//            if (gamepad2.a) {
//
//                // Toggle the servo position
//                if (servoPositionIsZero) {
//                    planeServo.setPosition(45);  //
//                } else if (gamepad2.a) {
//                   planeServo.setPosition(0.0);
//                }
//
//                servoPositionIsZero = !servoPositionIsZero; // Toggle the state
//                sleep(500); // Add a delay to prevent rapid toggling
//            }

            if (gamepad1.b) {

                clawServo.setPosition(.5);

            } else if (gamepad1.a) {
                clawServo.setPosition(0.0);
            }

            // Field-centric drive dt with control stick inputs:
            robot.drivetrain.run(
                    gamepadEx1.getLeftX(),
                    gamepadEx1.getLeftY(),
                    gamepadEx1.getRightX()
            );

            mTelemetry.update();
        }
        robot.interrupt();
    }
}