import React from 'react';
import {Form, FormControl, FormField, FormItem, FormMessage} from "@/components/ui/form.jsx";
import {Input} from "@/components/ui/input.jsx";
import {DialogClose} from "@/components/ui/dialog.jsx";
import {Button} from "@/components/ui/button.jsx";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select.jsx";
import {useForm} from "react-hook-form";
import {tags} from "@/Pages/ProjectList/ProjectList.jsx";
import {Cross1Icon} from "@radix-ui/react-icons";
import {useDispatch} from "react-redux";
import login from "@/Pages/Auth/Login.jsx";
import {createProjects} from "@/Redux/Project/Action.js";

const CreateProjectForm = () => {

    const dispatch = useDispatch();

    const handleTagsChange = (newValue) => {
        const currentTags = form.getValues("tags")
        const updatedTags = currentTags.includes(newValue)?
            currentTags.filter(tag => tag.name !== newValue) : [...currentTags, newValue];

        form.setValue("tags", updatedTags);
    }

    const form = useForm({
        defaultValues: {
            projectName: "",
            description: "",
            category: "",
            tags: []
        }
    });

    const onSubmit = (data) => {
        dispatch(createProjects(data));
        console.log("Create Project Data",data);
    };

    return (
        <Form {...form}>
            <form className="space-y-5" onSubmit={form.handleSubmit(onSubmit)}>
                <FormField control = {form.control}
                           name="projectName"
                           render={({field}) => <FormItem>
                               <FormControl>
                                   <Input{...field}
                                   type="text"
                                   className="border w-full border-gray-700 py-5 px-5"
                                   placeholder="Project Name"/>
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
                                         placeholder="Project Description"/>
                               </FormControl>
                               <FormMessage>

                               </FormMessage>
                           </FormItem>}
                />

                <FormField
                    control={form.control}
                    name="category"
                    render={({ field }) => (
                        <FormItem>
                            <FormControl>
                                <Select
                                    defaultValue="Full-Stack"
                                    value={field.value}
                                    onValueChange={(value) => field.onChange(value)}
                                >
                                    <SelectTrigger className="w-full">
                                        <SelectValue placeholder="Category" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        <SelectItem value="Full-Stack">Full Stack</SelectItem>
                                        <SelectItem value="Front-End">Front End</SelectItem>
                                        <SelectItem value="Back-End">Back End</SelectItem>
                                    </SelectContent>
                                </Select>
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <FormField
                    control={form.control}
                    name="tags"
                    render={({ field }) => (
                        <FormItem>
                            <FormControl>
                                <Select
                                    onValueChange={(value) => {
                                        handleTagsChange(value)
                                    }}
                                >
                                    <SelectTrigger className="w-full">
                                        <SelectValue placeholder="Tags" />
                                    </SelectTrigger>
                                    <SelectContent>
                                        {tags.map((item) => (
                                            <SelectItem key={item} value={item}>
                                                {item}
                                            </SelectItem>
                                        ))}
                                    </SelectContent>
                                </Select>
                            </FormControl>
                            {field.value.map((item) => <div key={item} onClick={() => handleTagsChange(item)} className="flex gap-1 flex-wrap">
                                <div className="cursor-pointer flex rounded-full items-center border gap-2 px-4 py-1">
                                    <span className="text-sm">{item}</span>
                                    <Cross1Icon className="h-3 w-3"/>
                                </div>
                            </div>
                            )}
                            <FormMessage />
                        </FormItem>
                    )}
                />

                <DialogClose>
                    {false ? (
                        <div>
                            <p>You can only create 3 Projects with free plan, please upgrade your plan to create more Projects</p>
                        </div>
                    ) : (
                        <Button type="submit" className="w-full mt-5">
                            Create Project
                        </Button>
                    )}
                </DialogClose>

            </form>
        </Form>
    )
}

export default CreateProjectForm;