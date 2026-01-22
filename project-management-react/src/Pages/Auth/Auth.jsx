import React, {useState} from 'react';
import Signup from "@/Pages/Auth/Signup.jsx";
import Login from "@/Pages/Auth/Login.jsx";
import {Button} from "@/components/ui/button.jsx";


const Auth = () => {

    const [active, setActive] = useState(true);

    return (
        <div className="loginContainer flex justify-center items-center h-screen pt-20">
            <div className="box h-[30rem] w-[25rem]">
                <div className="loginBox w-full px-10 space-y-5">
                    {active ? <Signup/> : <Login/>}
                    <div>
                        <span>{active ? "Already have an account?" : "Don't have an account?"}</span>
                        <Button
                            className="ml-2"
                            variant="ghost"
                            onClick={() => setActive(!active)}>
                            {active ? "Sign-In" : "Sign-Up"}
                        </Button>
                    </div>
                </div>
            </div>
        </div>
    )
}

export default Auth