package guru.springframework.converters;


import guru.springframework.commands.NoteCommand;
import guru.springframework.domain.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class NoteCommandToNotes implements Converter<NoteCommand, Notes> {
    @Override
    public Notes convert(NoteCommand noteCommand) {
        if(noteCommand==null) {
            return null;
        }
        final Notes notes=new Notes();
        notes.setRecipeNotes(noteCommand.getRecipeNotes());
        notes.setId(noteCommand.getId());
        return notes;
    }
}
