import React from 'react';
import {useForm} from "react-hook-form";
import {Form, FormControl, FormField, FormItem, FormMessage} from "@/components/ui/form.jsx";
import {Input} from "@/components/ui/input.jsx";
import {DialogClose} from "@/components/ui/dialog.jsx";
import {Button} from "@/components/ui/button.jsx";
import {useDispatch} from "react-redux";
import {createIssue} from "@/Redux/Issue/Action.js";
import {useParams} from "react-router-dom";

const CreateIssueForm = ({status}) => {

    const dispatch = useDispatch();

    const {id} = useParams();

    const form = useForm({
        defaultValues: {
            issueName: "",
            description: ""
        }
    });

    const onSubmit = (data) => {
        data.projectId = id;

        dispatch(
            createIssue({
                title: data.issueName,
                description: data.description,
                projectId: id,
                status,
            })
        );
        console.log("Create Issue Data",data);
    }

    return (
        <div>
            <Form {...form}>
                <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
                    <FormField control = {form.control}
                               name="issueName"
                               render={({field}) => <FormItem>
                                   <FormControl>
                                       <Input{...field}
                                             type="text"
                                             className="border w-full border-gray-700 py-5 px-5"
                                             placeholder="Issue Name"/>
                                   </FormControl>
                                   <FormMessage>

                                   </FormMessage>
                               </FormItem>}
                    />

                    <FormField control = {form.control}
                               name="description"
                               render={({field}) => <FormItem>
                                   <FormControl>
                                       <Input{...field}
                                             type="text"
                                             className="border w-full border-gray-700 py-5 px-5"
                                             placeholder="Description"/>
                                   </FormControl>
                                   <FormMessage>

                                   </FormMessage>
                               </FormItem>}
                    />

                    <DialogClose>
                        <Button type="submit" className="w-full mt-5">
                            Create Issue
                        </Button>
                    </DialogClose>

                </form>
            </Form>
        </div>
    )
}

export default CreateIssueForm;