/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GmaovTestModule } from '../../../test.module';
import { LocalisationsDetailComponent } from 'app/entities/localisations/localisations-detail.component';
import { Localisations } from 'app/shared/model/localisations.model';

describe('Component Tests', () => {
    describe('Localisations Management Detail Component', () => {
        let comp: LocalisationsDetailComponent;
        let fixture: ComponentFixture<LocalisationsDetailComponent>;
        const route = ({ data: of({ localisations: new Localisations(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [GmaovTestModule],
                declarations: [LocalisationsDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(LocalisationsDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(LocalisationsDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.localisations).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
