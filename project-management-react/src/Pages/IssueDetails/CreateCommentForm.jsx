import React from 'react';
import {useForm} from "react-hook-form";
import {Form, FormControl, FormField, FormItem, FormMessage} from "@/components/ui/form.jsx";
import {Input} from "@/components/ui/input.jsx";
import {Button} from "@/components/ui/button.jsx";
import {Avatar, AvatarFallback} from "@/components/ui/avatar.jsx";
import {useDispatch} from "react-redux";
import {createComment} from "@/Redux/Comment/Action.js";

const CreateCommentForm = ({issueId}) => {

    const dispatch = useDispatch();

    const form = useForm({
        defaultValues: {
            content: ""
        }
    });

    const onSubmit = (data) => {
        dispatch(createComment({content: data.content, issueId}));
        console.log("Create Project Data",data);
    }

    return (
        <div className="">
            <Form {...form}>
                <form className="flex gap-2" onSubmit={form.handleSubmit(onSubmit)}>
                    <FormField control = {form.control}
                               name="content"
                               render={({field}) =>
                                   <FormItem>
                                       <div className="flex gap-2">
                                           <div>
                                               <Avatar>
                                                   <AvatarFallback>C</AvatarFallback>
                                               </Avatar>
                                           </div>
                                           <FormControl>
                                               <Input{...field}
                                                     type="text"
                                                     className="w-[20rem]"
                                                     placeholder="Add Comment Here"/>
                                           </FormControl>
                                       </div>

                                       <FormMessage>

                                       </FormMessage>
                                   </FormItem>}
                    />

                    <Button type="submit">
                        Save
                    </Button>


                </form>
            </Form>
        </div>
    )
}

export default CreateCommentForm