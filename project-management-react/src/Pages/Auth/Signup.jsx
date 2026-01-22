import React from 'react';
import {useForm} from "react-hook-form";
import {Form, FormControl, FormField, FormItem, FormMessage} from "@/components/ui/form.jsx";
import {Input} from "@/components/ui/input.jsx";
import {Button} from "@/components/ui/button.jsx";
import {useDispatch} from "react-redux";
import {register} from "@/Redux/Auth/Action.js";

const Signup = () => {

    const dispatch = useDispatch();

    const form = useForm({
        defaultValues: {
            email: "",
            password: "",
            fullName: "",
        }
    });

    const onSubmit = (data) => {
        dispatch(register(data));
        console.log("Create Project Data",data);
    }

    return (
        <div className="border py-3 px-5 rounded-lg space-y-5">
            <h1 className="">Register</h1>
            <Form {...form}>
                <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
                    <FormField control = {form.control}
                               name="fullName"
                               render={({field}) => (
                                   <FormItem>
                                       <FormControl>
                                           <Input{...field}
                                                 type="text"
                                                 className="border w-full border-gray-700 py-5 px-5"
                                                 placeholder="Full Name"/>
                                       </FormControl>
                                       <FormMessage>

                                       </FormMessage>
                                   </FormItem>)}
                    />
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
                        Register
                    </Button>

                </form>
            </Form>
        </div>
    )
}

export default Signup;