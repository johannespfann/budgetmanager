package de.pfann.budgetmanager.database;

import android.provider.ContactsContract;

import java.util.List;

import de.pfann.budgetmanager.model.Category;
import de.pfann.budgetmanager.model.Entry;
import de.pfann.budgetmanager.model.Tag;

/**
 * Created by johannes on 31.03.15.
 */
public class DatabaseAccessorFacade {

    CategoryMapper mCategoryMapper;
    EntryMapper mEntryMapper;
    TagMapper mTagMapper;


    public DatabaseAccessorFacade(CategoryMapper aCategoryMapper,final EntryMapper aEntryMapper,final TagMapper aTagMapper){
        mEntryMapper = aEntryMapper;
        mCategoryMapper = aCategoryMapper;
        mTagMapper = aTagMapper;
    }

    public Category persistCategory(final String aName){
        return mCategoryMapper.persistCategory(aName);
    }

    public boolean categoryAlreadyExists(final String aName){
        return mCategoryMapper.categoryAlreadyExits(aName);
    }

    public List<Category> getAllCategories(){
        List<Category> categories = mCategoryMapper.getAllCategories();
        for(Category category : categories){
            category.setTags(mTagMapper.getAllTagsByCategory(category));
        }
        return categories;
    }

    public void deleteCategory(final Category aCategory){
        List<Entry> entries = aCategory.getAllEntries();
        for(Entry entry : entries){
            deleteEntry(entry);
        }
        List<Tag> tags = aCategory.getTags();
        for(Tag tag : aCategory.getTags()){

        }
    }

    public Entry persistEntry(final String aName, final double aSum,final String aMemo,final List<Tag> aTags ,final Category aCategory){
        Entry newEntry = mEntryMapper.persistEntry(aName,aSum,aMemo,aTags,aCategory);
        aCategory.addEntry(newEntry);
        return newEntry;
    }

    public void deleteEntry(final Entry aEntry){
        aEntry.getCategory().getAllEntries().remove(aEntry);
        mEntryMapper.deleteEntry(aEntry);
    }

    public boolean tagAlreadyExists(final String aName, final Category aCategory){
        return mTagMapper.tagAlreadyExists(aName,aCategory);
    }

    public Tag persistTag(final String aName,final Category aCategory){
        Tag newTag = mTagMapper.persistTag(aName,aCategory);
        aCategory.addTag(newTag);
        return newTag;
    }

    public void deleteTag(final Tag aTag){
        Category category = aTag.getCategory();
        category.getTags().remove(aTag);
        List<Entry> entries = category.getAllEntries();
        for(Entry entry : entries){
            entry.getTags().remove(aTag);
        }

    }





}
