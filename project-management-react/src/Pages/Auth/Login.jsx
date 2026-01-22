import React from 'react';
import {useForm} from "react-hook-form";
import {Form, FormControl, FormField, FormItem, FormMessage} from "@/components/ui/form.jsx";
import {Input} from "@/components/ui/input.jsx";
import {Button} from "@/components/ui/button.jsx";
import {useDispatch} from "react-redux";
import {login} from "@/Redux/Auth/Action.js";

const Login = () => {

    const dispatch = useDispatch();

    const form = useForm({
        defaultValues: {
            email: "",
            password: "",
        }
    });

    const onSubmit = (data) => {
        dispatch(login(data));
        console.log("Login Project Data",data);
    }

    return (
        <div className="border py-3 px-5 rounded-lg space-y-5">
            <h1>Sign In</h1>
            <Form {...form}>
                <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
                    <FormField control = {form.control}
                               name="email"
                               render={({field}) => (
                                   <FormItem>
                                       <FormControl>
                                           <Input{...field}
                                                 type="email"
                                                 className="border w-full border-gray-700 py-5 px-5"
                                                 placeholder="Email"/>
                                       </FormControl>
                                       <FormMessage>

                                       </FormMessage>
                                   </FormItem>)}
                    />
                    <FormField control = {form.control}
                               name="password"
                               render={({field}) => (
                                   <FormItem>
                                       <FormControl>
                                           <Input{...field}
                                                 type="password"
                                                 className="border w-full border-gray-700 py-5 px-5"
                                                 placeholder="Password"/>
                                       </FormControl>
                                       <FormMessage>

                                       </FormMessage>
                                   </FormItem>)}
                    />

                    <Button type="submit" className="w-full mt-5">
                        Login
                    </Button>

                </form>
            </Form>
        </div>
    )
}

export default Login;