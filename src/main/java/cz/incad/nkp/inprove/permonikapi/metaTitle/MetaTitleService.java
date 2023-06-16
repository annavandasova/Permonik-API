package cz.incad.nkp.inprove.permonikapi.metaTitle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetaTitleService {

    private final MetaTitleRepository metaTitleRepository;

    @Autowired
    public MetaTitleService(MetaTitleRepository metaTitleRepository) {
        this.metaTitleRepository = metaTitleRepository;
    }


    public List<MetaTitle> getAll(){
        Iterable<MetaTitle> iterable = metaTitleRepository.findAll();
        return Streamable.of(iterable).toList();
    }

}
